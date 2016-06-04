(ns p-frame.handlers
  (:require [re-frame.frame]
            [re-frame.middleware :as mw]))

(def handlers
  {:initialize-db
   (fn  [_ _] db/default-db)

   :set-kv
   (fn [db [_ k v]] (assoc db k v))

   :test
   (fn [db _] (assoc db :name (rand)))})
