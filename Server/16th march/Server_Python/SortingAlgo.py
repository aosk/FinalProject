class Test():
    def __init__(self):

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
        print("\n\n\n-------------Before:")
        for i in usersLocation:
                print i
        print("-------------------------------------\n")

        index=0
        group=[]
        for i in usersLocation:
            x = int(abs(i[1]*1000000))
            y = int(abs(i[2]*1000000))
            group.append((i[0],(x,y)))




        print "+++++++++++++ Cleaned:"
        for i in group:
                print i
        print "+++++++++++++++++++++++++++++\n\n"

        self.__group = group

        x_list=[]
        #create list of x_axises
        for i in self.__group:
            x_list.append(i[1][0])
        #sort the group based on the list of x_axises
        self.radixsort(x_list,self.__group)
        print("============ X Sorted: \n")
        for i in self.__group:
            print i
        print "==============================\n\n"
        j=1
        i=0
        result_list=[]
        while(i<len(self.__group)):
            small_list=[]
            small_list.append(self.__group[i])
            # --------- !! 
            if(j==len(self.__group)):
                result_list.append(small_list)
                break
            while(abs(self.__group[i][1][0]-self.__group[j][1][0])<20):
                small_list.append(self.__group[j])
                if(j==(len(self.__group)-1)):
                        break
                j+=1

            result_list.append(small_list)
            i=j
            j+=1
        final_result = result_list

        #creating smaller list
        for i in range(len(result_list)):
            y_list=[]
            for j in range(len(final_result[i])):
                y_list.append(result_list[i][j][1][1])
            self.radixsort(y_list,final_result[i])

        print("============ Y Sorted: \n")
        for i in final_result:
            for item in i:
                print item
        print "==============================\n\n"


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
                while(abs(final_result[i][j][1][1]-final_result[i][j2][1][1]) < 15):
                    small_list.append(final_result[i][j2])
                    if(j2==(len(final_result[i])-1)):
                        done=True
                        break
                    j2+=1
                result_list.append(small_list)

                #print "---!---",small_list, "---!---"
                if(done):
                    print "BREAK ..!!..........."
                    break
                j=j2
                j2+=1
        for i in result_list:
                print"\n Result_list:\n"
                print i



    '''
    =====================================================================
                  ***** Sorting Fun  *****
    =====================================================================
    '''
            
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
Test()
"""
test_group = [("D",(10,11)),("C",(3,4)),("E",(12,13)),("B",(2,45)),("F",(100,101)),("G",(500,501)), ("A",(1,50))]  
list_x_axis=[10,3,12,2,100,500,1]
radixsort(list_x_axis,test_group)
print(list_x_axis)
print(test_group)
"""
#sort(test_group)
#test_group = ["A":(1,50),"B":(2,45),"C":(3,4),"D":(10,11),"E":(12,13),"F":(100:101),"G":(500,501)] 
#take difference
#small_group[["A":(1,50),"B":(2,45),"C":(3,4)],["D":(10,11),"E":(12,13)],["F":(100:101)],["G":(500,501)]
#lists_y_axis=[[50,45,4],[11,13],[101],[501]]
#smaller_group[{"C":(3,4)},{"A":(1,50),"B":(2,45)},{"D":(10,11),"E":(12,13)},{"F":(100:101)},{"G":(500,501)}]
#for i in smaller_group:
#Generate event.....
