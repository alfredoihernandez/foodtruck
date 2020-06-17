(ns take_home_engineering_challenge.webserver.app_routes
  (:require [compojure.core :refer [GET POST defroutes]]))

(defroutes app-routes
  (GET "/" request "Hello world"))