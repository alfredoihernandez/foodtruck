(ns take-home-engineering-challenge.components.webserver
  (:require [ring.middleware.json :as json]
            [ring.adapter.jetty :as jetty]
            [ring.logger :as logger]
            [take-home-engineering-challenge.webserver.app_routes :refer [app-routes]]
))

(def handler
  (-> app-routes
      json/wrap-json-response
      logger/wrap-with-logger
))

(defonce web-server (jetty/run-jetty handler {:port 3000 :join? false}))