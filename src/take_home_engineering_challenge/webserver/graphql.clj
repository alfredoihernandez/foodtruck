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

(defrecord FoodTrucks []
  data/Resolvable
  (resolve!
    [_ _]
    (foodtrucks/cached-data)))

(def QueryRoot
  {
   :food-trucks (map->FoodTrucks {})
})

(def graphql-handler
  (graphql/handler
   {:schema schema
    :query  QueryRoot}))