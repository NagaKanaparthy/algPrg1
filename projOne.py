import math, random, time
def generateTestPairs():
	pairs = []
	for mag in range(1,16,1):
		minV = math.pow(10,mag-1)
		maxV = math.pow(10,mag)
		#print str(minV)+','+str(maxV)
		valOne = random.randrange(minV,maxV,2)
		random.seed()
		valTwo = random.randrange(minV,maxV,3)
		pairs.append([valOne,valTwo])
	return pairs
def measureTime(functionToMeasure,pair):
	start = time.time()
	res = functionToMeasure(pair[0],pair[1])
	timeV = str(time.time() - start)
	if(res is None):
		return timeV+",None" 
	else:
		return timeV+","+str(res)
def euclidGCD(valOne,valTwo):
	if(valTwo > valOne):
		temp = valOne
		valOne = valTwo
		valTwo = temp
	if(valOne == 1 and valTwo == 0):
		return None
	if(valTwo == 0):
		return valOne
	return euclidGCD(valTwo, valOne % valTwo)
def exhaustiveGCD(valOne,valTwo):
	primesOne = []
	primesTwo = []
#	print int(math.floor(math.sqrt(valOne)))
#	print str(range(2,int(math.floor(math.sqrt(valOne))),1))
	#Find primes of valOne
	for divisor in reversed(range(2,valOne,1)):
		if(valOne % divisor == 0):
			#print str(divisor)
			primesOne.append(divisor)
	#Find primes of valTwo
	for divisor in reversed(range(2,valTwo,1)):
                if(valTwo % divisor == 0):
                        #print str(divisor)
                        primesTwo.append(divisor)
	#Find GCD	
	for onePrime in primesOne:
		if onePrime in primesTwo:
			return onePrime	
	return None
testPairs = generateTestPairs()
print "Val1,Val2,Exhaustive Time,Val,Euclid time,Val"
for pair in testPairs:
	print str(pair[0])+','+str(pair[1])+","+\
		measureTime(exhaustiveGCD,pair)+","+\
		measureTime(euclidGCD,pair)
