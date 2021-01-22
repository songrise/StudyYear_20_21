import numpy as np

ch_en_table = {
    "使恶化": ["aggravate", "Stress and lack of sleep can aggravate the situation."],
    "减轻，缓和": ['alleviate', "Nowadays, a great deal can be done to alleviate back pain."],
    "势在必行的": ["imperative", "It is imperative for students to study hard at school."],
    "巨大的；急剧的；引人注目的； 激动人心的 ": ["dramatic", "A fifth year of drought is expected to have dramatic effects on the California economy."],
    "前提": ["premise", "Diligence is the premise of a bright future."],
    "恶化": ["deteriorate", "There are fears that the situation might deteriorate into full-scale war."],
    "享有声望的，受尊敬的，地位显赫的": ["prestigious", "It's one of the best equipped and most prestigious schools in the country."],
    "言过其实": ["exaggerate",
             "It is admitted that marks really encourage the students to learn, but their importance should not be exaggerated."],

}


def test(corpus: dict):
    num_key = len(corpus)
    keys = list(corpus.keys())
    print("Instruction: input '?' for hint")
    while(True):
        print("==============================")
        i = np.random.randint(0, num_key)
        key = keys[i]
        print(key, end=": ")
        ans = input()

        if ans == '?':  # get some hint
            print("the word starts with letter ", corpus[key][0][0])
            ans = input("input your answer: ")

        if not is_correct(key, ans, corpus):
            print("*Wrong!* Correct answer: " + corpus[key][0])
        else:
            print("*Correct!*")

        try:
            print("E.g.: "+corpus[key][1])  # sentence
        except IndexError as ignored:
            pass


def is_correct(key: str, val: str, corpus: dict) -> bool:
    val = val.lower().strip()
    return val == corpus[key][0]  # supppose key is always correct


if __name__ == "__main__":
    test(ch_en_table)
