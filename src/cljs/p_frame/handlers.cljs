(ns p-frame.handlers
  (:require [re-frame.frame]
            [re-frame.middleware :as mw]))

(defn test-handler [db [n]]
  (js/console.log "db: " db)
  (js/console.log n)
  (assoc @db :name (rand)))

(def handlers
  {:initialize-db
   (fn  [_ _] {:name "Joel Spolsky"})

   :set-kv
   (fn [db [_ k v]] (assoc db k v))

   :test test-handler})
