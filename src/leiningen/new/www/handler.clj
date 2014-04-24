(ns {{name}}.handler
  (:require [{{name}}.layout :refer [say]]
            [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]))

(defroutes app-routes

  (GET "/" [] (say "I am ready to be created!"))

  (route/resources "/")
  (route/not-found "not found"))

(def app
  (handler/site app-routes))
