(ns p-frame.views
  (:require [p-frame.db :as db]))

(defn main-panel []
  (let [name (db/subscribe [:name])]
    (fn []
      [:div "Hello im " (pr-str @name)])))
