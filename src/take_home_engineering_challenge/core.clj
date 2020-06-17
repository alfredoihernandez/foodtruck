(ns take_home_engineering_challenge.core
  (:require [taoensso.timbre :as log]
            [take_home_engineering_challenge.components.webserver :refer [web-server]]
            [take_home_engineering_challenge.services.config :refer [gather-configs]]
            ))

(defn start-web-server []
  (.start web-server))

(defn stop-web-server []
  (.stop web-server))

(defn -main
  ""
  [& _]
  (let [configs gather-configs]
    (log/info "Configs:" configs)
    (log/info "Starting WebServer")
    (start-web-server)
    (log/info "Started WebServer")
))