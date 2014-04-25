(ns leiningen.new.www
  (:use [leiningen.new.templates :only [renderer name-to-path ->files]]))

(def render (renderer "www"))

(defn www
  "Creates web project templates of different flavors: Om, ClojureScript (+ nREPL), Compojure, Hiccup, Ring, etc."
  ([name] 
   (www name :noop))
  ([name opts]
   (let [data {:name name
               :sanitized (name-to-path name)}
         base [["src/{{sanitized}}/{{name}}.clj" (render "www.clj" data)]         ;; server wisdom placeholder
               ["src/{{sanitized}}/layout.clj" (render "layout.clj" data)]        ;; sample layout
               ["src/{{sanitized}}/handler.clj" (render "handler.clj" data)]      ;; routes
               ["src/{{sanitized}}/cljs/{{name}}.cljs" (render "www.cljs" data)]  ;; client wisdom placeholder
               ["src/{{sanitized}}/cljs/tools.cljs" (render "tools.cljs" data)]   ;; local storage & console logging

               ["test/{{sanitized}}/test.clj" (render "test.clj" data)]           ;; server test entry point

               ["resources/public/css/{{name}}.css" (render "www.css" data)]
               ["project.clj" (render "project.clj" data)]
               [".gitignore" (render "gitignore" data)]]
         brepl [["test/{{sanitized}}/brepl.clj" (render "brepl.clj" data)]
                ["src/{{sanitized}}/layout.clj" (render "brepl/layout.clj" data)]
                ["project.clj" (render "brepl/project.clj" data)]]
         om    [["src/{{sanitized}}/cljs/{{name}}.cljs" (render "om/www.cljs" data)]  ;; om "hello world"
                ["src/{{sanitized}}/layout.clj" (render "om/layout.clj" data)]
                ["project.clj" (render "om/project.clj" data)]]]

     (apply ->files data
       (case opts
         "with-brepl" (concat base brepl)
         "with-om" (concat base brepl om)
         base)))))
