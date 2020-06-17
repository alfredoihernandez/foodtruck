(defproject take_home_engineering_challenge "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [csv-map "0.1.2"]
                 [org.clojure/core.memoize "0.5.9"]
                 [ring/ring-core "1.7.1"]
                 [ring/ring-json "0.5.0"]
                 [ring/ring-jetty-adapter "1.7.1"]
                 [ring-logger "1.0.1"]
                 [com.taoensso/timbre "4.10.0"]
                 [compojure "1.6.1"]
                 [haversine "0.1.1"]
                 [alumbra "0.2.6"]
                 [alumbra/claro "0.1.14"]]
  :main ^:skip-aot take_home_engineering_challenge.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
