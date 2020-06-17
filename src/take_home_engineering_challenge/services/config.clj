(ns take_home_engineering_challenge.services.config
  (:require [clojure.edn :as edn]))

(def gather-configs (edn/read-string (slurp "src/take_home_engineering_challenge/resources/config.edn")))