(ns curator.shared-lock
  "Implements shared locks.

  Fully distributed locks that are globally synchronous, meaning at any snapshot
  in time no two clients think they hold the same lock.

  Note: unlike InterProcessMutex this lock is not reentrant.

  https://curator.apache.org/curator-recipes/shared-lock.html"
  (:import (org.apache.curator.framework.recipes.locks InterProcessSemaphoreMutex)
           (java.util.concurrent TimeUnit)))

(defn ^InterProcessSemaphoreMutex shared-lock
  [curator-framework ^String path]
  (InterProcessSemaphoreMutex. curator-framework path))

(defn acquire
  "Acquire the mutex - blocking until it's available.

  Each call to acquire must be balanced by a call to release."
  [^InterProcessSemaphoreMutex lock]
  {:pre [(instance? InterProcessSemaphoreMutex lock)]}
  (.acquire lock))

(defn acquire-with-timeout
  "Acquire the mutex - blocks until it's available or the given time expires.

  Return false if the lock could not be acquired in the given time.

  Must be balanced by a call to release."
  [^InterProcessSemaphoreMutex lock time unit]
  {:pre [(instance? InterProcessSemaphoreMutex lock)
         (integer? time)
         (instance? TimeUnit unit)]}
  (.acquire lock time unit))

(defn release
  "Perform one release of the mutex."
  [^InterProcessSemaphoreMutex lock]
  {:pre [(instance? InterProcessSemaphoreMutex lock)]}
  (.release lock))
