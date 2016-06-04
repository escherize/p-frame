(ns p-frame.subs
  (:require [re-frame.frame :as frame]
            [re-frame.middleware :as mw])
  (:require-macros [reagent.ratom :refer [reaction]]))

(def subscriptions
  {:name (fn [db [n]] (reaction (:name @db)))})
