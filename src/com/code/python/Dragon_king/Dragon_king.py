def flames(name1, name2):
    # Convert names to lowercase and remove spaces
    name1 = name1.lower().replace(" ", "")
    name2 = name2.lower().replace(" ", "")
    for char in name1:
        if char in name2:
            name1 = name1.replace(char, "", 1)
            name2 = name2.replace(char, "", 1)
    
    count = len(name1) + len(name2)
    
    sequence = "flames"
    
    # Eliminate letters from the sequence based on the count
    while len(sequence) > 1:
        index = (count % len(sequence)) - 1
        if index >= 0:
            sequence = sequence[:index] + sequence[index+1:]
        else:
            sequence = sequence[:len(sequence)-1]
    
    # Map the final letter to the status
    status_map = {
        'f': 'Friendship',
        'l': 'Love',
        'a': 'Affection',
        'm': 'Marriage',
        'e': 'Enemy',
        's': 'Sibling'
    }
    
    # Return the status
    return status_map[sequence]
