import threading
class Test(threading.Thread):
        
    def __init__(self,threadID,group):
        threading.Thread.__init__(self)
        self.__group = group
        self.threadID = str(threadID)
        self.__events=[]
    def run(self):
        print("The Thread "+self.threadID+" is running.")
        #print(self.__group)
        x_list=[]
        #create list of x_axises
        for i in self.__group:
            x_list.append(i[1][0])
        #sort the group based on the list of x_axises
        self.radixsort(x_list,self.__group)
        j=1
        i=0
        done=False
        result_list=[]

        while(i<len(self.__group)):
            small_list=[]
            small_list.append(self.__group[i])
            # --------- !! 
            if(j==len(self.__group)):
                result_list.append(small_list)
                break
            while(abs(self.__group[i][1][0]-self.__group[j][1][0])<5):
                small_list.append(self.__group[j])
                if(j==(len(self.__group)-1)):
                    done=True
                    break
                j+=1
            if(done):
                break
            result_list.append(small_list)
            i=j
            j+=1
        final_result = result_list[:]


        #creating smaller list
        for i in range(len(result_list)):
            y_list=[]
            for j in range(len(final_result[i])):
                y_list.append(result_list[i][j][1][1])
            self.radixsort(y_list,final_result[i])
        result_list=[]


        for i in range(len(final_result)):
            j2=1
            j=0
            done=False
            while(j<len(final_result[i])):
                small_list=[]
                small_list.append(final_result[i][j])
                if(j2==len(final_result[i])):
                    result_list.append(small_list)
                    break
                while(abs(final_result[i][j][1][1]-final_result[i][j2][1][1]) < 50):
                    small_list.append(final_result[i][j2])
                    if(j2==(len(final_result[i])-1)):
                        done=True
                        break
                    j2+=1
                result_list.append(small_list)

                print small_list
                if(done):
                    break
                j=j2
                j2+=1
                

        print "\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"
        print "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"
        for i in result_list:
            print i
        print "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"
        print "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n"
        

        for i in result_list:
            if(len(i)>2):
                self.__events.append(i)
    def getEvents(self):

        return self.__events

    def radixsort(self, aList,bList ):
        RADIX = 10
        maxLength = False
        tmp , placement = -1, 1
        while not maxLength:
            maxLength = True
            # declare and initialize buckets
            buckets = [list() for _ in range( RADIX )]
            buckets2= [list() for _ in range( RADIX )]
            bi=0
            # split aList between lists
            for  i in aList:
                tmp = i / placement
                buckets[int(tmp % RADIX)].append( i )
                buckets2[int(tmp % RADIX)].append(bList[bi])
                bi+=1
                if maxLength and tmp > 0:
                    maxLength = False

            # empty lists into aList array
            a = 0
            for b in range( RADIX ):
                buck = buckets[b]
                bi=0
                for i in buck:
                    aList[a] = i
                    bList[a] = buckets2[b][bi]
                    a += 1
                    bi+=1

            # move to next digit
            placement *= RADIX
# calculate the distance between


#[("D",(10,11)),("C",(3,4)),("E",(12,13)),("B",(2,45)),("F",(100,101)),("G",(500,501)), ("A",(1,50))]
'''
print("\n\n\n-----------Before:-----------")
print usersLocation
print("----------------------------\n\n\n")
x =0
y= 0

for i in usersLocation:
    for item in i:
        if(index > 6):
            x = int(abs(item[0]*1000000))
            print item[0], item[1]
            y = int(abs(item[1]*1000000))
        group.append((i[0],(x,y)))
    index+=1


print("-----------Fixed:-----------")
print(group)
print("-----------------------------\n\n\n")
my_thread=Test(1,group)
my_thread.start()
print("----")
print(my_thread.getEvents())
print("----")
my_thread.join()
print("Exiting Main Thread")
'''

import MySQLdb
from datetime import datetime


PORT_NUMBER = 8000

# Set up DB connection
db = MySQLdb.connect(host="127.0.0.1",    # Host: localhost
                     user="root",         # root
                     passwd="",  # empty
                     db="letsunite2")        # letsunite2

cur = db.cursor()
cur.execute("SELECT group_id, latitude , longitude FROM user_info")
usersLocation = list(cur)
print("\n\n\n-------------Before:-----------------")
for i in usersLocation:
    print i
print("-------------------------------------\n")




index=0
group=[]
for i in usersLocation:
    x = int(abs(i[1]*1000000))
    y = int(abs(i[2]*1000000))
    group.append((i[0],(x,y)))


print("\n-------------After Clean up:-----------------")
for i in group:
    print i
print("---------------------------------------------\n\n")



my_thread=Test(1,group)
my_thread.start()
print("----")
print my_thread.getEvents()
print("----")
my_thread.join()
print("Exiting Main Thread")
