(ns {{name}}.layout
  (:require
    [cemerick.austin.repls :refer [browser-connected-repl-js]]
    [hiccup
      [page :refer [html5 include-js include-css]]]))

(defn with-css []
  (list
   ;; more css e.g. (include-css "/css/bootstrap.css")
   (include-css "/css/{{name}}.css")))

(defn with-js []
  (list
   ;; more js e.g. (include-js "//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js")   
   (include-js "http://fb.me/react-0.9.0.js")
   (include-js "/js/{{name}}.js")
   [:script (browser-connected-repl-js)]))

(defn say [content]
  (html5
    [:head
      [:title "{{name}}"]
      [:meta {"name" "viewport" "content" "width=device-width, initial-scale=1.0"}]
      (with-css)]
    [:body
      [:div {:id "app"} content]
      (with-js)]))
