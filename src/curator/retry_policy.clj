(ns curator.retry-policy
  "Create instances of org.apache.curator.RetryPolicy implementations.

  See http://curator.apache.org/apidocs/org/apache/curator/retry/package-summary.html"
  (:import [org.apache.curator.retry RetryForever
            RetryOneTime
            RetryNTimes
            RetryUntilElapsed
            ExponentialBackoffRetry
            BoundedExponentialBackoffRetry]))

(defn retry-forever
  "RetryPolicy implementation that always allowsRetry."
  [retry-interval-ms]
  (RetryForever. retry-interval-ms))

(defn retry-one-time
  "A retry policy that retries only once."
  [sleep-ms-between-retry]
  (RetryOneTime. sleep-ms-between-retry))

(defn retry-n-times
  "Retry policy that retries a max number of times."
  [n sleep-ms-between-retry]
  (RetryNTimes. n sleep-ms-between-retry))

(defn retry-until-elapsed
  "A retry policy that retries until a given amount of time elapses."
  [max-elapsed-times-ms sleep-ms-between-retry]
  (RetryUntilElapsed. max-elapsed-times-ms sleep-ms-between-retry))

(defn exponential-backoff-retry
  "Retry policy that retries a set number of times with increasing sleep time between retries."
  ([base-sleep-time-ms max-retries]
   (ExponentialBackoffRetry. base-sleep-time-ms max-retries))
  ([base-sleep-time-ms max-retries max-sleep-ms]
   (ExponentialBackoffRetry. base-sleep-time-ms max-retries max-sleep-ms)))

(defn bounded-exponential-backoff-retry
  "Retry policy that retries a set number of times with an increasing (up to a maximum bound) sleep time between retries."
  [base-sleep-time-ms max-sleep-ms max-tries]
  (BoundedExponentialBackoffRetry. base-sleep-time-ms max-sleep-ms max-tries))
