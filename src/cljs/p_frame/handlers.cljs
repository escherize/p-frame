(ns p-frame.handlers
  (:require [re-frame.frame :as frame]
            [re-frame.middleware :as mw]))

(defn test-handler [db _]
  (assoc db :name (gensym "Test!!")))

(def handlers
  :initialize-db
  (fn  [_ _] {:name "Joel Spolsky"})

  :test test-handler)
