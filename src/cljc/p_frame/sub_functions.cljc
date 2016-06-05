(ns p-frame.sub-functions)

(defn name
  ([db] (name db nil))
  ([db _] (:name db)))
