(ns take_home_engineering_challenge.webserver.app_routes
  (:require [compojure.core :refer [GET POST defroutes]]
            [take_home_engineering_challenge.webserver.graphql :refer [graphql-handler]]))

(defroutes app-routes
  (POST "/graphql" request (graphql-handler request))
)