(ns porc.core
  (:require [rtp.calculator :as calc]
            [rtp.generator :as gen])
  (:gen-class))

(defn -main
  "Gives the RTP for the current prize-table"
  [& args]
  (println "Let's find this RTP...")
  (println (calc/rtp gen/eq-hands)))
        
