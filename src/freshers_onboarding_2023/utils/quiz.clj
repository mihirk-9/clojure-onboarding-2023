(ns freshers-onboarding-2023.utils.quiz
  (:require [freshers-onboarding-2023.utils.questions :refer [get-random-questions
                                                              get-score-for-question]]
            [clojure.string :as cstr]))

(def ^:private current-quiz (ref []))

(def ^:private question-index (ref 0))

(def ^:private final-score (ref 0))


(defn- ^:private ask-for-choice
  "Prints question, asks user for choice and returns user's choice"
  [{:keys [question options]}]
  (println "\n\n" question)
  (println (->> options
                (map-indexed (fn [option-index option]
                               (str option-index
                                    " > "
                                    option)))
                (cstr/join "\n")))
  (print "Your choice:")
  (flush)
  (when-let [user-choice (Integer/parseUnsignedInt (read-line))]
    (println " " user-choice)
    user-choice))


(defn- ^:private init-quiz
  "Initializes quiz with random questions. Number of questions is dictated by question-count"
  [question-count]
  (dosync
   (ref-set current-quiz
            (get-random-questions :question-count question-count))
   (ref-set question-index 0)
   (ref-set final-score 0)))


(defn start-quiz
  "Starts quiz"
  [question-count]
  (init-quiz question-count)
  (doseq [_ (range question-count)]
    (let [{:keys [id] :as current-question} (@current-quiz @question-index)
          user-choice (ask-for-choice current-question)]
      ;; FIXME math ain't mathin'
      (dosync
       (ref-set final-score
                (get-score-for-question id
                                        user-choice))
       (ref-set question-index
                (inc @question-index)))))
  (println "Your final score is " @final-score))
