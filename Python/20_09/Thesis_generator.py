from random import *


class RandTitle:
    quan = ["The", "A"]

    noun1 = ["model", "data", "experiment", "method",
             "approach", "review", "explanation", "measurement"]

    noun2 = ["circuit",  "neuron",
             "memories", "cognition", "thermography", "cerebral", "database", "oxidation", "gene", "algorithm", "electrode", "enzyme", "apparatus", "hyperplasia", "population", "sensors", "dementia", "COVID-19", "atom", "amino acid", "metabolism", "neural network", "engineering", "infrared", "nanoparticles", "dairy products", "superconductors", "insulators",
             "transformer", "hydro power", "plant cell", "relativity", "photosynthesis", "conductivity", "antigen", "temperatures", "lateralization", "radiation", "pollution",
             "cosmology", "errestrial heat", "glacier", "orbit", "hydrocarbon", "gravitation", "calculus", "statistics", "anatomy", "legislation", "tariff", "constitution", "immunity", "aquatic plan"]

    adj = ["eastern", "deep", "advanced",
           "adaptive", "qualitative", "evolutionary", "refined",
           "environmental", "optimal", "underestimated", "concurrent", "stochastic", "chronic", "distributed",
           "implantable", "linear", "systematic", "quantitative", "centralized", "overturning"]

    conj = ["and", "of", "with", "for"]

    @classmethod
    def rand_word(cls, word_list: list) -> str:
        return word_list[randint(0, len(word_list)-1)]

    @classmethod
    def gen_title(cls, n: int):
        for _ in range(n):
            i = randint(1, 5)
            if i == 1:
                yield " ".join([cls.rand_word(cls.quan), cls.rand_word(cls.adj), cls.rand_word(cls.noun1), cls.rand_word(cls.conj), cls.rand_word(cls.noun2)]).capitalize()
            elif i == 2:
                yield " ".join([cls.rand_word(cls.adj), cls.rand_word(cls.noun2), cls.rand_word(cls.noun2)]).capitalize()
            elif i == 3:
                yield " ".join([cls.rand_word(cls.quan), cls.rand_word(cls.adj), cls.rand_word(cls.noun2), cls.rand_word(cls.conj), cls.rand_word(cls.noun2)]).capitalize()
            elif i == 4:
                yield " ".join([cls.rand_word(cls.adj), cls.rand_word(cls.noun1), cls.rand_word(cls.conj), cls.rand_word(cls.noun2)]).capitalize()
            elif i == 5:
                yield " ".join([cls.rand_word(cls.adj), cls.rand_word(cls.noun2), cls.rand_word(cls.conj), cls.rand_word(cls.noun2)]).capitalize()


if __name__ == "__main__":
    for _ in RandTitle.gen_title(10):
        print(_)
