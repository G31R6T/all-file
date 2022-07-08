︠c81bbc29-51d8-4954-b9fd-73d9af93931cas︠
%auto
typeset_mode(True, display=False)
︡605d4c47-3265-46bf-be71-e9a5788eaa64︡{"done":true}
︠798502eb-1a43-45e8-b46c-1c8e82f0d902︠
# One-time pad (will use this later)
def encrypt_xor(plain, key):
    n = min(len(plain), len(key))
    ascii_codes = [ord(plain[i]) ^^ ord(key[i]) for i in range(n)]
    return ''.join([chr(x) for x in ascii_codes])
︡9bfde644-ad6c-40b7-853f-db620b3d5733︡︡
︠6785b97b-d30c-4447-b78f-608ab4d00ab8i︠
%html
<p>From last time</p>
<p>3. Let's make our own random number generator: Let $a_0$ a seven-digit number (seed). Then generate $a_{n+1}$ by taking the final 7 digits of $a_n^2$. Let our random sequence at position $n$ be 0 or 1 depending on whether $a_n$'s third digit is even or odd. Is this a good random function (how to check)? Write a function that computes the period length depending on the seed.</p>

︡bda09892-3210-4ec6-8a1f-5b06cd3fe87e︡{"html": "<p>From last time</p>\n<p>3. Let's make our own random number generator: Let $a_0$ a seven-digit number (seed). Then generate $a_{n+1}$ by taking the final 7 digits of $a_n^2$. Let our random sequence at position $n$ be 0 or 1 depending on whether $a_n$'s third digit is even or odd. Is this a good random function (how to check)? Write a function that computes the period length depending on the seed.</p>"}︡
︠7be11eb9-0244-4a75-a010-3e8cfe972716︠
# Problem 3: to 
def third_dig(n):
    return floor(n%100000 / 10000)
︡9b186c6a-b716-4120-99e5-fe5426c4f312︡︡
︠1ed6648a-123a-4805-8730-23cabc757276︠
(1234567 % 100000) / 10000.0
︡76cb11c0-215e-4e48-a13f-4324110d3391︡{"stdout": "3.45670000000000"}︡
︠87fb26ac-8fcd-4bef-afca-d6475524759c︠
# Third digit of 25: seven-digits 0000025
︡9848443a-45ec-4df7-930b-7ca00b54ddd0︡︡
︠6713ad4d-8328-4451-b822-2d5512ae4874︠
third_dig(1234567)
︡ee18dd65-eb87-4a47-897f-ae43b6551ab0︡{"stdout": "3"}︡
︠7234c324-f53e-40f2-bbb8-d271f0e59e1a︠
third_dig(1101111)
︡8eac6c77-d975-4695-a03f-200d1daa790e︡{"stdout": "0"}︡
︠cec44e90-8705-4ad0-a9c3-f09afb1b1532︠
third_dig(1234)
︡d3710110-e4c9-4d2b-9f52-8e9992032447︡{"stdout": "0"}︡
︠b5cae752-318b-49d7-8898-dcf9b4c4187a︠
# Parameters: seed, length
def rand_seq(seed, N):
    ret = []
    last = seed
    for i in range(N):
        ret.append(third_dig(last) % 2)
        last = (last^2) % 10^7
    return ret
︡0af7cfe8-3e51-44a0-8651-281e12beb5cb︡︡
︠e8b4c38a-e9a5-4262-a7f3-93c18a70e9eb︠
rand_seq(1234567, 20)
︡17632e6d-50a1-49b8-831e-c8811078b7ad︡{"stdout": "[1, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1]"}︡
︠b49ffb60-168e-475f-8fc2-0090d8355ce2︠
rand_seq(5, 20)
︡f6922172-0a69-451e-91cd-0480f3cf9e2a︡{"stdout": "[0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]"}︡
︠74b3bbc8-639a-43d6-88bf-e14d24f01c4f︠
def period_of_rand_seq(seed):
    last = seed
    L = []
    N = 0
    while last not in L:
        L.append(last)
        N += 1
        last = (last^2) % 10^7
        #print last
    return N
︡872385a0-a010-4701-a2a5-0eaaaeb2cbb2︡︡
︠e1e21639-5c58-4a9c-9378-e1907310e2f9︠
period_of_rand_seq(1234567)
︡84a0e04d-4e0e-4f21-afc4-443e780a8aa9︡{"stdout": "12504"}︡
︠79553d6b-d7f3-4824-805c-a7358d6f0334︠
period_of_rand_seq(1)
︡57788289-d196-4284-bcd0-000d9f3c8648︡{"stdout": "1"}︡
︠bcd0adc0-6dc4-4b83-a08a-77957b611d65︠
[period_of_rand_seq(K) for K in [1..10]]
︡5a288928-3eae-47c4-ba36-842c7f577981︡{"stdout": "[1, 12503, 12505, 12502, 6, 12503, 2504, 12502, 12504, 4]"}︡
︠28da6bbf-d9b0-4b16-9200-c40319da2145︠
period_of_rand_seq(5)
︡2edb853f-c208-455a-9255-421cacf52c67︡{"stdout": "25\n625\n390625\n7890625\n2890625\n2890625\n6"}︡
︠e1344b22-c11d-446f-a81b-a2c497184dd4︠
2890625^2
︡8d86d2b4-148a-4c32-81b6-a431f0181943︡{"stdout": "8355712890625"}︡
︠c6cc2d71-3766-4fbc-b1d6-033214e1b00e︠
5127657^2
︡fb358ee8-7a65-46a1-9390-49c160ae4a70︡{"stdout": "26292866309649"}︡
︠6acf9bb3-0ee8-4e8d-9084-c91dcb9702c8︠
[period_of_rand_seq(K) for K in [91..100]]
︡bbbbada2-f8af-426c-8231-e3cb0efb4ba1︡{"stdout": "[12505, 12502, 2505, 12503, 4, 12501, 12502, 12503, 2505, 3]"}︡
︠ad1c8326-665f-4942-b728-7678a242565b︠
# Period not long enough -> not 'one-time'. Information leaked.
︡cf4ec67e-4131-4cdc-abbc-3d5dde3408a9︡︡
︠d4e1d28c-9bd2-43d9-8d97-12e0f346f8aai︠
%html
<h2>Goal: look into indistinguishability</h2>
<p>"Game":&nbsp; two plaintexts and a single ciphertext are given. Guess which plainext the ciphertext corresponds to. If guesses siginificantly better than 50%: we are in trouble.</p>

︡eca0c0fa-9f1c-4917-b133-7fa3b513f761︡{"html": "<h2>Goal: look into indistinguishability</h2>\n<p>\"Game\":&nbsp; two plaintexts and a single ciphertext are given. Guess which plainext the ciphertext corresponds to. If guesses siginificantly better than 50%: we are in trouble.</p>"}︡
︠a4c401b0-f52f-4be9-b1e3-dfc5dd776c61︠
# Example 1. 
# Plaintexts: "AAAAAAAA", "BBBBBBBB" (or longer versions)
# Method: XOR with pseudorandom function's output
# Key: uniformly random integer between 1 and 10000
# (Pseudo)randomness: take powers of seed, Output 0 if last digit: 0, 1, 2, 3, 4, output 1 if last digit is 5, 6, 7, 8, 9.
︡cb9b2602-0a4a-4739-a762-7533d6bb5283︡︡
︠3f8feaca-e262-4e00-98f8-ec997d39acde︠
[2^k%10 for k in [1..10]]
︡e0e1af5d-96d5-420c-a478-9ca58b544a59︡{"stdout": "[2, 4, 8, 6, 2, 4, 8, 6, 2, 4]"}︡
︠d3c914aa-60f0-4ca6-9917-11c26b2102b6︠
# E.g.
def rand_gen_ex1(seed, N):
    return [0 if (seed^k)%10 <5 else 1     for k in range(N)]
︡2b76ebd5-efba-41e2-b2f5-be212765185e︡︡
︠87c945eb-43df-4593-ae51-5f7bfedcd657︠
rand_gen_ex1(2, 20)
︡745d0c49-8741-4f20-b9a2-e14111e831f5︡{"stdout": "[0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1]"}︡
︠8e3503a6-f36f-4bc4-9b07-a5bbfca407fb︠
rand_gen_ex1(11, 20)
︡512f1404-cdea-4714-90fa-08aad0d1c501︡{"stdout": "[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]"}︡
︠212984d9-5743-443c-b136-27a670df3174︠
rand_gen_ex1(1010, 20)
︡5338cdc0-450c-44af-8ede-e8ea08d3bc71︡{"stdout": "[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]"}︡
︠08a81d11-ca85-4071-90ad-58464ded7b1a︠
def convert_binary_list_to_str(L):
    ret = ""
    for i in range(floor(len(L) / 8)):
        ret += chr( sum([2^k * L[8*i+k] for k in range(8)] ) )
    return ret
︡65c4c2cc-9c80-4b55-8c22-1e8c57abccb6︡︡
︠d88fb285-d0b2-495d-b2d1-dbf01522f9aa︠
ord("A")
︡cd4f69cc-dbbb-4bdb-a728-a8929eb1a5af︡{"stdout": "65"}︡
︠e12adcdd-f5b4-4bee-84fc-c59056caa0d9︠
65.digits(2)
︡e1e407fd-1574-4792-8869-4045e1e3d160︡{"stdout": "[1, 0, 0, 0, 0, 0, 1]"}︡
︠4eb9252c-b5f7-4705-9fe1-176e49893a0a︠
L = 65.digits(2) + [0]
︡323d756e-fccb-4e0a-9b25-8f8ca0b3d360︡︡
︠d9f99f3d-901e-4f57-a7ff-54e3c2904328︠
convert_binary_list_to_str(L)
︡ec6cd5e8-0432-4d24-a150-915586f4e4cb︡{"stdout": "'A'"}︡
︠77b5ecd7-fdad-4c1e-b8c9-d91dc898d276︠
def output_random_ciphertext_ex1():
    m0 = "AAAAAAAA"
    m1 = "BBBBBBBB"
    plain = m0 if randint(0,1) == 0 else m1
    seed = randint(1, 10000)
    L = rand_gen_ex1(seed, len(plain)*8)
    k = convert_binary_list_to_str(L)
    return encrypt_xor(plain, k), plain
︡d140bca4-d590-4d1b-b2cc-d70fee4d939f︡︡
︠8356af8b-6f7d-49a0-bf5f-b10e4579c7c8︠
output_random_ciphertext_ex1()
︡cead7795-9177-4b71-9210-b2a00abdeea0︡{"stdout": "('\\xeb\\xeb\\xeb\\xeb\\xeb\\xeb\\xeb\\xeb', 'AAAAAAAA')"}︡
︠090b0abb-77ed-4484-b25e-5137c2d0f838︠
output_random_ciphertext_ex1()
︡34e0a9db-e102-4541-b604-76f33fa2b612︡{"stdout": "('\\xd9\\xd8\\xd8\\xd8\\xd8\\xd8\\xd8\\xd8', 'AAAAAAAA')"}︡
︠61d3686c-e9f9-4a06-b4d1-aab78c1758a6︠
output_random_ciphertext_ex1()
︡5b13860a-07d3-40cf-a002-e379eec730a2︡{"stdout": "('\\x15\\x14\\x14\\x14\\x14\\x14\\x14\\x14', 'AAAAAAAA')"}︡
︠18cdbccc-c131-4c75-92fe-5fe345c20e43︠
# Strategy 1: if AAAAAAAA or BBBBBBBB then I know for sure, otherwise guess
def tipp(s):
    L = ["AAAAAAAA", "BBBBBBBB"]
    if s in L:
        return s
    else:
        return L[randint(0,1)]
︡52fabac1-4ac1-4806-8c15-9cf58b99f5ac︡︡
︠b4756738-f8ae-447a-843c-d35265f7d604︠
def check_success_ratio(method, N):
    ret = 0
    for i in range(N):
        c, m = output_random_ciphertext_ex1()
        s = method(c)
        if s == m:
            ret += 1
    return ret
︡d2c3e503-a20d-4235-90a3-6fd9ef914674︡︡
︠0ebee65a-807b-45a7-ad10-46f7866b7940︠
check_success_ratio(tipp, 1000)
︡15bc1148-0cd0-4a9c-9d8c-ea794bf0b1ed︡{"stdout": "597"}︡
︠d3d7ede0-20f6-4245-b6d5-f3878ffbe8fc︠
# brute force decrypt, try all keys
def tipp2(s):
    seed = 0
    L = ["AAAAAAAA", "BBBBBBBB"]
    finished = False
    
    while not finished:
        L2 = rand_gen_ex1(seed, 64)
        k = convert_binary_list_to_str(L2)
        m = encrypt_xor(s, k)
        if m in L:
            finished = True
        seed += 1
    return m
︡bf0d827d-6267-4507-bc25-e6a7eb92065b︡︡
︠729a3be2-27a5-429b-b79b-a4810be04065︠
check_success_ratio(tipp2, 1000)
︡9149d018-c06b-4db4-99ae-a93df9315864︡{"stdout": "1000"}︡
︠a386d58a-99e8-402f-b5b9-a26ebe58b04f︠
# Example 2.
# Plaintexts: "AAAAAAAA", "BBBBBBBB" (or longer versions)
# Method: XOR with pseudorandom function's output
# Key: uniformly random integer between 1 and 10000
# (Pseudo)randomness: take powers of seed, Output 0 if _FIRST_ digit: 0, 1, 2, 3, 4, output 1 if _FIRST_ digit is 5, 6, 7, 8, 9.
︡6f40f69a-cf4a-482d-a6c2-a7daafa75543︡︡
︠1823a4c2-de0f-47b6-b203-fde6afe1ad4b︠
def rand_gen_ex2(seed, N):
    return [0 if str((seed^k))[0] in '01234' else 1     for k in range(N)]
︡610b9e6b-0ad3-4663-8324-1887754ef0f8︡︡
︠aa6ede25-feac-4b02-9f82-e32b1d94852f︠
rand_gen_ex2(18, 20)
︡f7bc3e19-b107-4857-893c-31a99864ec45︡{"stdout": "[0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1]"}︡
︠0af3ddc3-81ed-4ae5-ab45-772f581883ea︠
[18^k for k in range(20)]
︡26dda9a0-aca0-4279-801a-c30598bc206a︡{"stdout": "[1,\n 18,\n 324,\n 5832,\n 104976,\n 1889568,\n 34012224,\n 612220032,\n 11019960576,\n 198359290368,\n 3570467226624,\n 64268410079232,\n 1156831381426176,\n 20822964865671168,\n 374813367582081024,\n 6746640616477458432,\n 121439531096594251776,\n 2185911559738696531968,\n 39346408075296537575424,\n 708235345355337676357632]"}︡
︠06f8aeac-1df3-49c9-b243-eb6a9d25691c︠
def output_random_ciphertext_general(method):
    m0 = "AAAAAAAA"
    m1 = "BBBBBBBB"
    plain = m0 if randint(0,1) == 0 else m1
    seed = randint(1, 10000)
    L = method(seed, len(plain)*8)
    k = convert_binary_list_to_str(L)
    return encrypt_xor(plain, k), plain
︡9ed1e577-7ef2-413e-8efe-4ef7b8ebee30︡︡
︠570bc738-3261-4cdf-b65a-43f9e93b9076︠
output_random_ciphertext_general(rand_gen_ex2)
︡b654968a-d6d1-4cc1-b0b2-0e58289b6d3f︡{"stdout": "(' \\xceS\\x00\\xces\\x00J', 'BBBBBBBB')"}︡
︠01253a39-2f03-4100-841d-d0f60ba154fc︠
def check_success_ratio(method_rand_gen, method_guess, N=100):
    ret = 0
    for i in range(N):
        c, m = output_random_ciphertext_general(method_rand_gen)
        s = method_guess(c)
        if s == m:
            ret += 1
    return ret
︡f0f87850-508f-4d51-ba82-4d0a0a527a72︡︡
︠1695789b-c70a-491d-b15f-ce1fcd13285b︠
check_success_ratio(rand_gen_ex1, tipp, 1000)
︡b703344a-e507-43a3-afa8-67e70873412f︡{"stdout": "577"}︡
︠faeea701-18ab-4894-9804-f8b2ad5c1dc0︠
def guess_ex2(s):
    seed = 0
    L = ["AAAAAAAA", "BBBBBBBB"]
    finished = False
    
    while not finished:
        L2 = rand_gen_ex2(seed, 64)
        k = convert_binary_list_to_str(L2)
        m = encrypt_xor(s, k)
        if m in L:
            finished = True
        seed += 1
    return m
︡57e616b1-4bc1-46ca-b285-e275377e161f︡︡
︠42a0a5f9-fd22-4e8d-a73a-bc1a5eb15dff︠
check_success_ratio(rand_gen_ex2, guess_ex2, 100)
︡4817770b-3d3a-4927-af57-ce481e38487e︡{"stdout": "100"}︡
︠c4a66c19-64c4-4cde-9206-e48232df2410︠
# attempt 2:
# First look at the 0-1 statistic of the output of psrandom seq. at specific position:
def stats_pos(method, pos):
    cnt = 0
    for k in range(10000):
        L = method(k, pos+1)
        if L[pos]:
            cnt += 1
    return cnt
︡8c5965e2-4788-4c7c-adcf-120e984892ec︡︡
︠7fca4c6e-ab9a-4fc8-8a75-fe3024f86419︠
stats_pos(rand_gen_ex2, 8)
︡6b053b63-00b0-470b-bcd6-e081115f2987︡{"stdout": "3318"}︡
︠61f0ea04-dc76-49e8-ac58-d90f9afae4a6︠
stats_pos(rand_gen_ex2, 20)
︡553d087f-2ac5-4a0e-988e-e498678b1a47︡{"stdout": "3129"}︡
︠ca860429-1feb-4d0c-8eab-766d99228997︠
# "AAAAAAAA" -> binary: [1, 0, 0, 0, 0, 0, 1, 0]*8
# "BBBBBBBB" -> binary: [0, 1, 0, 0, 0, 0, 1, 0]*8
def guess_ex2_v2(s):
    L = ["BBBBBBBB", "AAAAAAAA"]
    return L[ ord(s[3]) % 2]
︡a815186e-ae2e-4b77-bb16-bc0023df8442︡︡
︠0e804009-1541-45f0-9a40-a7dc1981df02︠
check_success_ratio(rand_gen_ex2, guess_ex2_v2, 10000)
︡85c4d9ad-08f1-4527-a7ea-ace449a0e337︡{"stdout": "6914"}︡









