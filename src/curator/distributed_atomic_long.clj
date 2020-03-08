(ns curator.distributed-atomic-long
  "Implements distributed atomic long.

  https://curator.apache.org/curator-recipes/distributed-atomic-long.html"
  (:import (org.apache.curator.framework.recipes.atomic DistributedAtomicLong)))

(defn ^DistributedAtomicLong distributed-atomic-long
  "Create a distributed atomic long in optimistic mode.

  Requires the following parameters:
  - curator-framework: instance of Curator
  - counter-path: path of the counter (as a string)
  - retry-policy: a retry policy, see http://curator.apache.org/apidocs/index.html

  After each operation you MUST first check succeeded() which returns true
  if the operation succeeded."
  [curator-framework counter-path retry-policy]
  (DistributedAtomicLong. curator-framework
                          counter-path
                          retry-policy))

(defn current-value
  "Return the current value of a DistributedAtomicLong"
  [^DistributedAtomicLong distributed-atomic-long]
  (-> distributed-atomic-long
      (.get)
      (.preValue)))

(defn increment!
  "Add 1 to the current value. Return true on success, false otherwise."
  [^DistributedAtomicLong distributed-atomic-long]
  (-> distributed-atomic-long
      (.increment)
      (.succeeded)))

(defn decrement!
  "Subtract 1 from the current value. Return true on success, false otherwise."
  [^DistributedAtomicLong distributed-atomic-long]
  (-> distributed-atomic-long
      (.increment)
      (.succeeded)))

(defn add!
  "Add delta to the current value. Return true on success, false otherwise."
  [^DistributedAtomicLong distributed-atomic-long delta]
  (-> distributed-atomic-long
      (.add delta)
      (.succeeded)))

(defn subtract!
  "Subtract delta from the current value. Return true on success, false otherwise."
  [^DistributedAtomicLong distributed-atomic-long delta]
  (-> distributed-atomic-long
      (.subtract delta)
      (.succeeded)))

(defn force-set!
  "Forcibly sets the value of the counter without any guarantees of atomicity."
  [^DistributedAtomicLong distributed-atomic-long value]
  (.forceSet distributed-atomic-long value))