(ns leiningen.new.cccp
  (:use [leiningen.new.templates :only [renderer name-to-path ->files]]))

(def render (renderer "cccp"))

(defn cccp
  "Create a new Clojure + ClojureScript + Compojure + Ring project"
  ([name] 
   (cccp name :noop))
  ([name opts]
   (let [data {:name name
               :sanitized (name-to-path name)}
         base [["src/{{sanitized}}/{{name}}.clj" (render "cccp.clj" data)]        ;; server wisdom placeholder
               ["src/{{sanitized}}/layout.clj" (render "layout.clj" data)]        ;; sample layout
               ["src/{{sanitized}}/handler.clj" (render "handler.clj" data)]      ;; routes
               ["src/{{sanitized}}/cljs/{{name}}.cljs" (render "cccp.cljs" data)] ;; client wisdom placeholder
               ["src/{{sanitized}}/cljs/tools.cljs" (render "tools.cljs" data)]   ;; local storage & console logging

               ["test/{{sanitized}}/test.clj" (render "test.clj" data)]           ;; server test entry point

               ["resources/public/css/{{name}}.css" (render "cccp.css" data)]
               ["project.clj" (render "project.clj" data)]
               [".gitignore" (render "gitignore" data)]]
         brepl [["test/{{sanitized}}/brepl.clj" (render "brepl.clj" data)]
                ["src/{{sanitized}}/layout.clj" (render "brepl/layout.clj" data)]
                ["project.clj" (render "brepl/project.clj" data)]]]

     (apply ->files data
       (if (= opts ":with-brepl")
         (concat base brepl) 
         base)))))
