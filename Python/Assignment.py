def invert_dict(dictionary):    # just changed the parameter name from d to dictionary to make it meaningful
    inverse_Dictionary = dict()  # Initilized new dictionary and name it to inverse_Dictionary
    for key in dictionary:  # Iterate through the keys in the given dictionary
        val = dictionary[key]   # Assigning the value associated with the current key of the dictionary
        """
            Now we are groing to reverse the dictionary values, so first we check for the existence of the value of the original dictionary as a key of inverted dictionary variable.
            if it not exist we will add the key:value of the old dictionary as value:key to revert the old dictionary by making value od old dicitonary as key and key of old dictionary as value of the new inverse dictionary variable.
            If it exit then just add the key of the old dictionary to the existed key (value of old dicitonary) value.            
        """
        if val not in inverse_Dictionary:   # Checking if value of old dicitonary is in inverse_Dictionary variable or not as a key
            inverse_Dictionary[val] = [key] # If the value of old dicitonary does not exist as a key then making the key:value pair of old dictionary to value:key in the new inverse_Dictionary variable making old dictionary reverse.
        else:  # If value exist in inverse_Dictionary file as key, it will append the key of old dictionary as the value of the existed key.
            inverse_Dictionary[val].append(key) 
    return inverse_Dictionary   # Returning inverted Dictionary variable

# Open the Old_Dictionary.txt file for reading as Old_Dictionary
with open("Old_Dictionary.txt", "r") as Old_Dictionary:
    Dictionary_lines = Old_Dictionary.readlines()

My_Original_dict = {}   # Empty My_Original_dict variable dictionary to save the key:value of the Old_Dictionary.txt file.
for line in Dictionary_lines:   
    key, value = line.strip().split(": ")   # Spliting every line based of : to seperate key and value of the dictionary.
    My_Original_dict[key] = value   # adding key:value pair to the dicitonary My_Original_dict (original)

# invert_dict is a function which takes dictionary variable as a paramter and return the inverted dictionary
inverted_dict = invert_dict(My_Original_dict)   # Calling invert_dict function while sending My_Original_dict dictionary variable as an argument and it will assign the inverted dictionary, which the funtion will return to the inverted_dict variable variable.

# Opens the Inverted_Dictionary.txt file for writing, if not exist, it will create it as Inverted_Dictionary
with open("Inverted_Dictionary.txt", "w") as Inverted_Dictionary:
    for value, keys in inverted_dict.items():   # getting key and value from the inverted dictionary to write in the Inverted_Dictionary.txt file.
        Inverted_Dictionary.write(f"{value}: {keys[0]}\n")  # Writing the key:value of the inverted dictionary to Inverted_Dictionary.txt file and then going to next line.

# Thus this python script reads the dictionary file, revert and saving the reverted dictionary to a different file