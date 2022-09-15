import math
from collections import defaultdict

def solution(fees, records):
    answer = []
    total_fee_dict = defaultdict(int)
    check_parking_time = dict()

    # calculate parking time
    for record in records:
        time, car_num, context = record.split()
        h,m = map(int, time.split(':'))

        # if car already exists
        if car_num in check_parking_time:
            if m < check_parking_time[car_num][1]:
                m += 60
                h -=1
            total_fee_dict[car_num] += (h - check_parking_time[car_num][0]) * 60 + m - check_parking_time[car_num][1]
            check_parking_time.pop(car_num)
        else:
            check_parking_time[car_num] = (h,m)

    # check dict is empty
    if check_parking_time:
        for k,v in check_parking_time.items():
            total_fee_dict[k] += (23 - v[0]) * 60 + 59 - v[1]

    # calculate fees
    for k,v in total_fee_dict.items():
        temp_fee = fees[1]
        if fees[0] - v <0:
            v -= fees[0]
            temp_fee += math.ceil(v/fees[2]) * fees[3]
        total_fee_dict[k] = temp_fee

    for k in sorted(total_fee_dict):
        answer.append(total_fee_dict[k])

    return answer
