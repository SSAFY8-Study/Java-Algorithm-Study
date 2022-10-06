def isPrime(k):
    if k == 2 or k == 3: return True
    if k % 2 == 0 or k < 2: return False
    for i in range(3, int(k ** 0.5) + 1, 2):
        if k % i == 0:
            return False
    return True

def solution(n, k):
    answer = 0
    cvt = ""
    while n > 0:
        cvt += str(n % k)
        n = n //  k
    for c in ''.join(reversed(cvt)).split('0'):
        if c == "": continue
        if isPrime(int(c)):
            answer += 1
    return answer