(ns take_home_engineering_challenge.webserver.graphql
  (:require [taoensso.timbre :as log]
            [alumbra.core :as graphql]
            [claro.data :as data]
))

(def schema
  ;schema naming conventions: https://www.apollographql.com/docs/apollo-server/schema/schema/#:~:text=Naming%20conventions
  (slurp "src/take_home_engineering_challenge/resources/schema.graphql"))

(defrecord Person [id]
  data/Resolvable
  (resolve! [_ _]
    {:name    (str "Person #" id)
     :friends (map ->Person  (range (inc id) (+ id 3)))}))

(def QueryRoot
  {:person (map->Person {})
   :me     (map->Person {:id 0})})

(def graphql-handler
  (graphql/handler
   {:schema schema
    :query  QueryRoot}))