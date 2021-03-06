(defproject rborer/curator "0.0.11"
  :description "Clojurified Apache Curator"
  :url "https://github.com/rborer/curator"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.apache.curator/curator-recipes "2.13.0"]
                 [org.apache.curator/curator-framework "2.13.0"]
                 [org.apache.curator/curator-x-discovery "2.13.0"]]
  :profiles {:dev {:dependencies [[org.slf4j/log4j-over-slf4j "1.7.30"]
                                  [org.slf4j/slf4j-simple "1.7.30"]]
                   :exclusions [org.slf4j/slf4j-log4j12]}}
  :scm {:name "git"
        :url "https://github.com/rborer/curator"})
