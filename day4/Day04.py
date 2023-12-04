import collections
# I got really lazy and used Python..

def gettotal(data):
    lines = data.split("\n")
    total = 0
    for line in lines:
        wins, nums = line.split("|")[0].split(":")[1], line.split("|")[1]
        pattern = [win.strip() for win in wins.split()]
        numbers = [num.strip() for num in nums.split()]
        matches = sum([num in pattern for num in numbers])
        total += 0 if matches == 0 else 2 ** (matches-1)
    return total

def eviltotal(data):
    print("test1")
    lines = data.split("\n")
    amounts = [1 for i in lines]
    for i, line in enumerate(lines):
        x, y = map(str.split, line.split('|'))
        n = len(set(x).intersection(set(y)))
        for j in range(i+1, min(i+1+n, len(lines))):
            amounts[j] += amounts[i]
    return sum(amounts)


with open("./day04/i") as file:
    data = file.read().strip()

print(gettotal(data))
print(eviltotal(data))