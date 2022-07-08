︠34870de0-1d7a-4ba1-a299-ecd548f89d9bas︠
%auto
typeset_mode(True, display=False)
︡609c4281-1baa-45c7-bde8-3ed4d3bded52︡{"done":true}
︠f4517ee3-3aac-4e24-90b6-199a5751be83︠
3600 / 29.0
︡da719ea0-0fb2-403e-af39-3dedca3cb1ee︡{"stdout": "124.137931034483"}︡
︠4250e4ea-f29e-45a7-964c-e510d940e806︠
55*13*5 - 34*21*7
︡92be40a1-f85a-4277-bb65-a47f3d6ad336︡{"stdout": "-1423"}︡
︠7c51b1ae-e716-46c4-b872-69b00c736aa0︠
-1423 % (55*34)
︡65b41e30-b556-4f6e-9ce4-1474b3e86a5a︡{"stdout": "447"}︡
︠c386cfa7-43ee-4017-8bd1-cc5a4ca9a8e7︠
55*34
︡c3a8e8e1-7e5b-4898-8cb8-7b180f6399d9︡{"stdout": "1870"}︡
︠8cb92ac2-8f31-4013-8df1-3829a6c722cd︠
euler
︡0f9ae6ec-15d6-460b-a0cc-c8667b7e5141︡︡
︠e5df8117-ff99-4527-ac82-f2c3b79a082f︠
25^11 % 40
︡6dd940b5-8457-46ee-a871-45db9edfdc2f︡{"stdout": "25"}︡
︠ed18e683-2b7e-4fec-be7a-64cf98a6df01︠
23^25
︡ddf5a02c-5a41-4ff0-a136-58596ce9d3ef︡{"stdout": "11045767571919545466173812409689943"}︡
︠9271d561-22d4-4cb7-8609-92da90140ce9︠
2023^2025^2027 % 100
︡24f4af1b-910b-48c7-a635-cd0f610a7411︡{"stderr": "Traceback (most recent call last):\n  File \"<stdin>\", line 1, in <module>\n  File \"_sage_input_9.py\", line 10, in <module>\n    exec compile(u'open(\"___code___.py\",\"w\").write(\"# -*- coding: utf-8 -*-\\\\n\" + _support_.preparse_worksheet_cell(base64.b64decode(\"MjAyM14yMDI1XjIwMjcgJSAxMDA=\"),globals())+\"\\\\n\"); execfile(os.path.abspath(\"___code___.py\"))' + '\\n', '', 'single')\n  File \"\", line 1, in <module>\n    \n  File \"/private/var/folders/7b/4d7y24z51dl48754cr6_kr900000gn/T/tmpf_ppUE/___code___.py\", line 3, in <module>\n    exec compile(u'_sage_const_2023 **_sage_const_2025 **_sage_const_2027  % _sage_const_100 ' + '\\n', '', 'single')\n  File \"\", line 1, in <module>\n    \n  File \"sage/rings/integer.pyx\", line 1914, in sage.rings.integer.Integer.__pow__ (/Applications/SageMath/src/build/cythonized/sage/rings/integer.c:13272)\nRuntimeError: exponent must be at most 9223372036854775807"}︡
︠5e699818-7fea-4496-9cc0-4a01e11e871b︠
# Problem 4A
R = IntegerModRing(100)
base = R(2023)
︡6541bbe6-d795-4c09-8882-93f32582b557︡{"stderr": "Traceback (most recent call last):\n  File \"<stdin>\", line 1, in <module>\n  File \"_sage_input_14.py\", line 10, in <module>\n    exec compile(u'open(\"___code___.py\",\"w\").write(\"# -*- coding: utf-8 -*-\\\\n\" + _support_.preparse_worksheet_cell(base64.b64decode(\"UHJvYmxlbSA0QVIgPSBJbnRlZ2VyTW9kUmluZygxMDApCmJhc2UgPSBSKDIwMjMp\"),globals())+\"\\\\n\"); execfile(os.path.abspath(\"___code___.py\"))' + '\\n', '', 'single')\n  File \"\", line 1, in <module>\n    \n  File \"/private/var/folders/7b/4d7y24z51dl48754cr6_kr900000gn/T/tmpJwSR5L/___code___.py\", line 3\n    Problem 4AR = IntegerModRing(_sage_const_100 )\n            ^\nSyntaxError: invalid syntax"}︡
︠074f1ee9-425e-45b9-8f88-ba1d7802ea40︠
base ^ (2025^2027)
︡f40878b6-63a6-4dac-976d-72378c401a67︡{"stdout": "43"}︡
︠d9a37791-0c90-4c5b-ad57-f8d48af46916︠
# Problem 3
# Squares mod 7:
[x^2 % 7 for x in [0..6]]
︡1f2770cc-2f41-4dfd-a0c8-7ef46ccf8533︡{"stdout": "[0, 1, 4, 2, 2, 4, 1]"}︡
︠42cc074f-55b6-4126-842d-5b6aef3a993b︠
def is_square_modp(x, p):
    for y in [0..p-1]:
        if (y^2 - x) % p == 0:
            return True
    return False
︡9260e11c-7838-49b0-882f-c8d92543bd97︡︡
︠44c94e49-71fd-4e68-9bf9-7b41e7f48d74︠
def do_we_have_five_squares_modp(p):
    for k in [0..2*p]:
        if is_square_modp(k, p) and is_square_modp(k+1, p) and is_square_modp(k+2, p) and is_square_modp(k+3, p) and is_square_modp(k+4, p):
            return True
    return False
︡9094b478-95b6-4151-883d-bdc6dbb712be︡︡
︠35e33702-cef5-434a-b29a-400c5212498a︠
[(p, do_we_have_five_squares_modp(p)) for p in [1..100] if is_prime(p)]
︡82498261-7b1c-4aa9-bd3b-cb43098b6264︡{"stdout": "[(2, True),\n (3, False),\n (5, False),\n (7, False),\n (11, False),\n (13, False),\n (17, True),\n (19, False),\n (23, True),\n (29, False),\n (31, False),\n (37, False),\n (41, True),\n (43, True),\n (47, True),\n (53, False),\n (59, True),\n (61, True),\n (67, True),\n (71, True),\n (73, True),\n (79, True),\n (83, True),\n (89, True),\n (97, True)]"}︡
︠c97a28c0-dcf6-40e2-871c-ed7ce9da4da1︠
[(x, is_square_modp(x, 7)) for x in [0..10]]
︡084007b9-f796-4a6b-bb1f-6c988140c88f︡{"stdout": "[(0, True),\n (1, True),\n (2, True),\n (3, False),\n (4, True),\n (5, False),\n (6, False),\n (7, True),\n (8, True),\n (9, True),\n (10, False)]"}︡
︠63d97677-2ec3-4549-8044-1e7fe77caac0︠
p = 17
[(x, is_square_modp(x, p)) for x in [0..2*p]]
︡a78b15c1-faeb-4492-83ef-b8d940ed8562︡{"stdout": "[(0, True),\n (1, True),\n (2, True),\n (3, False),\n (4, True),\n (5, False),\n (6, False),\n (7, False),\n (8, True),\n (9, True),\n (10, False),\n (11, False),\n (12, False),\n (13, True),\n (14, False),\n (15, True),\n (16, True),\n (17, True),\n (18, True),\n (19, True),\n (20, False),\n (21, True),\n (22, False),\n (23, False),\n (24, False),\n (25, True),\n (26, True),\n (27, False),\n (28, False),\n (29, False),\n (30, True),\n (31, False),\n (32, True),\n (33, True),\n (34, True)]"}︡
︠e914fb37-e705-4f82-b1db-fd751aabc3a2︠
# Problem 5
R = IntegerModRing(187)
︡3955d923-ee88-457a-9091-dab5e5f44fc9︡︡
︠85a55ddc-9442-474a-8eea-59d68d0fb989︠
R(3) ^ 7
︡1a5b6e3f-2eb4-4f4c-970a-e5c6bb4ecbaf︡{"stdout": "130"}︡
︠5b97bbdb-13a6-4fcd-9316-dd0d4dee3bbf︠
R(29) ^ 7
︡59701aea-6a59-41a4-b656-0ca72db5d5c3︡{"stdout": "160"}︡
︠54faa224-2ab6-4035-8ecd-a3009d8a63a6︠
for K in [2..99]: 
    if R(130) ^ K == R(3):
        print(K)
︡f7ae0497-4595-4d1c-a8c8-e38f1b51105e︡{"stdout": "23"}︡
︠4883c23a-d68a-4e6a-8922-991449ac68ce︠
for K in [2..99]: 
    if R(160) ^ K == R(29):
        print(K)
︡d3454b2d-9401-4652-9b82-e179bbe651ff︡{"stdout": "23"}︡
︠b807b11c-0083-48c9-b0d9-49c6e7de2118︠
# Answer 23
# Lesson: Taking 7th roots = Taking 23rd powers
︡be9eb38b-d486-4264-97ac-28a317a860ef︡︡
︠1fefc31e-8d06-4da1-8e23-c989551274ba︠
A ^ B = C
A, B known: C = ? exponentiation
A, C known: B = ? logarithm (base A)
B, C known: A = ? roots (Bth roots)
︡ce6bf9dc-ce5d-4d99-9010-3851d25489ec︡{"stderr": "Traceback (most recent call last):\n  File \"<stdin>\", line 1, in <module>\n  File \"_sage_input_38.py\", line 10, in <module>\n    exec compile(u'open(\"___code___.py\",\"w\").write(\"# -*- coding: utf-8 -*-\\\\n\" + _support_.preparse_worksheet_cell(base64.b64decode(\"QSBeIEIgPSBD\"),globals())+\"\\\\n\"); execfile(os.path.abspath(\"___code___.py\"))' + '\\n', '', 'single')\n  File \"\", line 1, in <module>\n    \n  File \"/private/var/folders/7b/4d7y24z51dl48754cr6_kr900000gn/T/tmpxU2i0t/___code___.py\", line 2\n    A ** B = C\nSyntaxError: can't assign to operator"}︡









