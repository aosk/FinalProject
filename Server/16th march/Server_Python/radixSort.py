class Test():
        def __init__(self):
                
                self.__group =[("DIT",(53339929,6260730)),("DCU",(53313282,6261120)),("UCD",(53337663,6262615)),("DIT",(53339932,6260762)),("UCC",(53307059,6255922)),("UCD",(53337669,6262595)), ("DIT",(53339948,6260730))] 

                #[("D",(53336426,6257412)),("C",(53336438,6257239)),("E",(53.336361,6257539)),("B",(53339870,6260456)),("F",(53339819,6260142)),("G",(53339798,6260002)), ("A",(53337657,6262672))]

                #[("D",(53.336426,-6.257412)),("C",(53.336438,-6.257239)),("E",(53.336361,-6.257539)),("B",(53.339870,-6.260456)),("F",(53.339819,-6.260142)),("G",(53.339798,-6.260002)), ("A",(53.337657,-6.262672))]  

                #[("D",(10,11)),("C",(3,4)),("E",(12,13)),("B",(2,45)),("F",(100,101)),("G",(500,501)), ("A",(1,50))]  

                x_list=[]
                #create list of x_axises
                for i in self.__group:
                        x_list.append(i[1][0])
                #sort the group based on the list of x_axises
                self.radixsort(x_list,self.__group)
                print("Group: \n")
                print(self.__group)
                print "\n\n"
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

                for i in result_list:
                        print"\n Result_list:\n"
                        print(i)
                
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