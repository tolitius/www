(ns {{name}}.layout
  (:require
    [hiccup
      [page :refer [html5 include-js include-css]]]))

(defn with-css []
  (list
   ;; more css e.g. (include-css "/css/bootstrap")
   (include-css "/css/{{name}}.css")))

(defn with-js []
  (list
   ;; more js e.g. (include-js "/js/jquery-1.10.2.min.js")   
   (include-js "/js/{{name}}.js")))

(defn say [content]
  (html5
    [:head
      [:title "{{name}}"]
      [:meta {"name" "viewport" "content" "width=device-width, initial-scale=1.0"}]
      (with-css)]
    [:body
      content
      (with-js)]))
