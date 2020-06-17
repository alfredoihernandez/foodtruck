(ns take-home-engineering-challenge.services.foodtrucks
  (:require [clojure.core.memoize :as memo]
            [taoensso.timbre :as log]
            [haversine.core :as haversine]
            [take-home-engineering-challenge.components.sf_gov_adapter :refer [food-truck-data]]))

(def cached-data (memo/ttl food-truck-data :ttl/threshold 3600000))

(defn distance-between-two-coordinates [coor1 coor2]
  (let [[x1 y1] coor1
        [x2 y2] coor2]
    (if (not-any? nil? [x1 x2 y1 y1])
      (haversine/haversine {:latitude y1 :longitude x1} {:latitude y2 :longitude x2})
      nil
)))