(defproject {{name}} "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"

  :min-lein-version "2.3.4"

  :source-paths ["src" "src/{{name}}"]

  :dependencies [[compojure "1.1.6"]
                 [ring "1.2.1"]
                 [org.clojure/clojurescript "0.0-2202"]
                 [org.clojure/clojure "1.5.1"]
                 [hiccup "1.0.5"]]

  :plugins [[lein-ring "0.8.10"]
            [com.cemerick/austin "0.1.4"]
            [lein-cljsbuild "1.0.3"]]

  :hooks [leiningen.cljsbuild]

  :cljsbuild {
    :builds [{:source-paths ["src/{{name}}/cljs"]
              :compiler {:output-to "resources/public/js/{{name}}.js"
                         :optimizations :whitespace
                         :pretty-print true
                         ;; :source-map "resources/public/js/{{name}}.js.map"
                         }}]}

  :ring {:handler {{name}}.handler/app}

  :profiles
    {:dev {:dependencies [[ring-mock "0.1.5"]
                          [javax.servlet/servlet-api "2.5"]]

           :injections [(require '[{{name}}.brepl :refer [connect-to-browser]]
                                 '[cemerick.austin.repls])
                        (defn browser-repl-env []
                          (reset! cemerick.austin.repls/browser-repl-env
                                   (cemerick.austin/repl-env)))
                        (defn browser-repl []
                          (cemerick.austin.repls/cljs-repl
                            (browser-repl-env)))]}
     })
