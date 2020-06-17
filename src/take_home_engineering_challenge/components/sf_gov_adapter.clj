(ns take_home_engineering_challenge.components.sf_gov_adapter
  (:require [csv-map.core :as csv]
            [clojure.string :refer [blank?]]
            [taoensso.timbre :as log]))

(defn cleaned-string [s, type-wrapper]
  (if (blank? s)
    nil
    (cond
      (= type-wrapper str) s
      (= type-wrapper int) (if (= "0" s) nil (int (read-string s)))
      (= type-wrapper float) (if (= "0" s) nil (float (read-string s)))
)))

(defn clean-csv-row [row]
  ;using read-string for numerics (int, float)
  {
   :id (cleaned-string (row "locationid") str)
   :name (cleaned-string (row "Applicant") str)
   :type (cleaned-string (row "FacilityType") str)
   :address (cleaned-string (row "Address") str)
   :permit-status (cleaned-string (row "Status") str)
   :established (cleaned-string (row "Received") str)
   :cuisine-desc (cleaned-string (row "FoodItems") str)
   :latitude (cleaned-string (row "Latitude") float)
   :longitude (cleaned-string (row "Longitude") float)
   :distance nil
   })

(def food-truck-raw
  (log/info "Expensive call to sfgov.org")
  (->
   "https://data.sfgov.org/api/views/rqzj-sfat/rows.csv?accessType=DOWNLOAD"
   slurp
   csv/parse-csv))

(defn food-truck-data [] (map clean-csv-row food-truck-raw))