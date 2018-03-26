(ns rtp.value-generator
  (:gen-class))

(def dist
  (loop [hands []
         a (range 4 13)]
    (if (empty? a)
      hands
      (recur
        (loop [hands hands
               b (range 3 (first a))]
          (if (empty? b)
            hands
            (recur
              (loop [hands hands
                     c (range 2 (first b))]
                (if (empty? c)
                  hands
                  (recur
                    (loop [hands hands
                           d (range 1 (first c))]
                      (if (empty? d)
                        hands
                        (recur
                          (loop [hands hands
                                 e (range (first d))]
                            (if (empty? e)
                              hands
                              (recur
                                (conj hands
                                      [(first a)
                                       (first b)
                                       (first c)
                                       (first d)
                                       (first e)])
                                (rest e))))
                          (rest d))))
                    (rest c))))
              (rest b))))
        (rest a)))))

(def pair
  (loop [hands []
         a (range 13)]
    (if (empty? a)
      hands
      (recur
        (loop [hands hands
               b (range 2 13)]
          (if (empty? b)
            hands
            (recur
              (if (= (first a) (first b))
                hands
                (loop [hands hands
                       c (range 1 (first b))]
                  (if (empty? c)
                    hands
                    (recur
                      (if (= (first a) (first c))
                        hands
                        (loop [hands hands
                               d (range (first c))]
                          (if (empty? d)
                            hands
                            (recur
                              (if (= (first a) (first d))
                                hands
                                (conj hands
                                      [(first a)
                                       (first a)
                                       (first b)
                                       (first c)
                                       (first d)]))
                              (rest d)))))
                      (rest c)))))
              (rest b))))
        (rest a)))))

(def dpair
  (loop [hands []
         a (range 1 13)]
    (if (empty? a)
      hands
      (recur
        (loop [hands hands
               b (range (first a))]
          (if (empty? b)
            hands
            (recur
              (loop [hands hands
                     c (range 13)]
                (if (empty? c)
                  hands
                  (recur
                    (if (or (= (first a) (first c))
                            (= (first b) (first c)))
                      hands
                      (conj hands
                            [(first a)
                             (first a)
                             (first b)
                             (first b)
                             (first c)]))
                    (rest c))))
              (rest b))))
        (rest a)))))

(def trio
  (loop [hands []
         a (range 13)]
    (if (empty? a)
      hands
      (recur
        (loop [hands hands
               b (range 1 13)]
          (if (empty? b)
            hands
            (recur
              (if (= (first a) (first b))
                hands
                (loop [hands hands
                       c (range (first b))]
                  (if (empty? c)
                    hands
                    (recur
                      (if (= (first a) (first c))
                        hands
                        (conj hands
                              [(first a)
                               (first a)
                               (first a)
                               (first b)
                               (first c)]))
                      (rest c)))))
              (rest b))))
        (rest a)))))

(def full
  (loop [hands []
         a (range 13)]
    (if (empty? a)
      hands
      (recur
        (loop [hands hands
               b (range 13)]
          (if (empty? b)
            hands
            (recur
              (if (= (first a) (first b))
                hands
                (conj hands
                      [(first a)
                       (first a)
                       (first a)
                       (first b)
                       (first b)]))
              (rest b))))
        (rest a)))))

(def four 
  (loop [hands []
         a (range 13)]
    (if (empty? a)
      hands
      (recur
        (loop [hands hands
               b (range 13)]
          (if (empty? b)
            hands
            (recur
              (if (= (first a) (first b))
                hands
                (conj hands
                      [(first a)
                       (first a)
                       (first a)
                       (first a)
                       (first b)]))
              (rest b))))
        (rest a)))))
