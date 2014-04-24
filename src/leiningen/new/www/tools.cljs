(ns {{name}}.tools)

;; local storage

(defn local-storage? []
  (try 
    (.setItem js/localStorage "42" "42")
    (.getItem js/localStorage "42")
    true
    (catch js/Object e false)))

(defn ls-put [k v]
  (.setItem js/localStorage k v))

(defn ls-get [k]
  (.getItem js/localStorage k))

;; console logging

(defn info [& m]
  (.log js/console (apply str m)))

(defn info->js [m]
  (.log js/console (clj->js m)))
