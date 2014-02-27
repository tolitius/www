(ns leiningen.new.cccp
  (:use [leiningen.new.templates :only [renderer name-to-path ->files]]))

(def render (renderer "cccp"))

(defn cccp
  "Create a new Clojure + ClojureScript + Compojure + Ring project"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]

    (->files data

             ["src/{{sanitized}}/{{name}}.clj" (render "cccp.clj" data)]        ;; server wisdom placeholder
             ["src/{{sanitized}}/layout.clj" (render "layout.clj" data)]        ;; sample layout
             ["src/{{sanitized}}/handler.clj" (render "handler.clj" data)]      ;; routes
             ["src/{{sanitized}}/cljs/{{name}}.cljs" (render "cccp.cljs" data)] ;; client wisdom placeholder
             ["src/{{sanitized}}/cljs/tools.cljs" (render "tools.cljs" data)]   ;; local storage & console logging

             ["resources/public/css/{{name}}.css" (render "cccp.css" data)]
             ["project.clj" (render "project.clj" data)]
             [".gitignore" (render "gitignore" data)])))
