(ns take_home_engineering_challenge.webserver.graphql
  (:require [taoensso.timbre :as log]
            [alumbra.core :as graphql]
            [claro.data :as data]
            [take_home_engineering_challenge.services.config :refer [gather-configs]]
            [take_home_engineering_challenge.services.foodtrucks :as foodtrucks]
))

(def schema
  ;schema naming conventions: https://www.apollographql.com/docs/apollo-server/schema/schema/#:~:text=Naming%20conventions
  (slurp (:graphql-schema-path gather-configs)))

(defrecord FoodTrucks [user-latitude user-longitude filters order page-size page-idx]
  data/Resolvable
  (resolve!
    [_ _]
    (let [page-size-default (or page-size 5)
          page-idx-default (or page-idx 0)
          filters-default (or filters [])
          order-default (keyword (or order "distance"))]
      (log/info user-latitude)
      (log/info user-longitude)
      (log/info filters-default)
      (log/info order-default)
      (log/info page-size-default)
      (log/info page-idx-default)
      (-> (->> (foodtrucks/cached-data) ; get data
               (map #(assoc % :distance ; calculate distance
                            (foodtrucks/distance-between-two-coordinates
                             (vals (select-keys % [:latitude :longitude]))
                             [user-latitude user-longitude])))
               (filter #(some? (:distance %))) ; filtering
               (sort-by order-default) ; ordering
               (partition-all page-size-default)) ; pagination
          (nth page-idx-default)) ; pagination
      )))

(def QueryRoot
  {
   :food-trucks (map->FoodTrucks {})
})

(def graphql-handler
  (graphql/handler
   {:schema schema
    :query  QueryRoot}))