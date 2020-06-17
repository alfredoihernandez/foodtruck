(ns take_home_engineering_challenge.core
  (:require [taoensso.timbre :as log]
            [take_home_engineering_challenge.components.webserver :refer [web-server]]
            ))

(defn start-web-server []
  (.start web-server))

(defn stop-web-server []
  (.stop web-server))

(defn -main
  ""
  [& _]
  (log/info "Starting WebServer")
  (start-web-server)
  (log/info "Started WebServer")
)