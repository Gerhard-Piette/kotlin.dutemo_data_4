from functools import cmp_to_key


def add_item(item_list: list[str], file_path: str):
    list0 = list()
    set0 = set()
    with open(file_path, "r", encoding="utf-8") as file:
        contents = file.read()
        for item in contents.split("\n"):
            if item != "":
                if item not in set0:
                    item_list.append(item)
                    list0.append(item)
                    set0.add(item)
    clean_file(file_path, list0)


def clean_file(file_path: str, item_list: list[str]):
    sort_by_unicode(item_list)
    text = ""
    for item in item_list:
        if item != "":
            text += item + "\n"
    with open(file_path, "w", encoding="utf-8") as file:
        file.write(text.strip())


def create_Key_class(kotlin_folder_name:str) -> str:
    ret = "package com.volupism." + kotlin_folder_name + "\n"
    ret += "\n"
    ret += "import com.volupism.s2_0.S2" + "\n"
    ret += "\n"
    ret += "/**" + "\n"
    ret += " * Created by Creator.py" + "\n"
    ret += " */" + "\n"
    ret += "class Key {" + "\n"
    keyword_list = list()
    add_item(keyword_list, "other_keyword.u")
    add_item(keyword_list, "base_model_keyword.u")
    token_name_list = list()
    add_item(token_name_list, "token_name.u")
    item_list = list()
    item_list.extend(keyword_list)
    item_list.extend(token_name_list)
    sort_by_unicode(item_list)
    ret += "\n"
    ret += "companion object {"
    for item in item_list:
        ret += "\n" + "@JvmStatic"
        ret += "\n" + "val " + get_name(item) + ' = "' + item + '"'
    ret += "\n" + "}"
    # end of class
    ret += "\n" + "}"
    # Write class
    filePath = "../src/main/kotlin/com/volupism/" + kotlin_folder_name + "/Key.kt"
    with open(filePath, "w", encoding="utf-8") as file:
        file.write(ret)
    print("File written : " + filePath)
    return ret


def get_name(key: str) -> str:
    ret = key
    ret = ret.replace("'", "_Apo_")
    ret = ret.replace("?", "_QM_")
    ret = ret.replace("!", "_EM_")
    ret = ret.replace("=", "_EQ_")
    ret = ret.replace("<", "_LT_")
    ret = ret.replace(">", "_GT_")
    ret = ret.replace(".", "_Point_")
    ret = ret.replace(",", "_Comma_")
    ret = ret.replace(";", "_Semicolon_")
    ret = ret.replace(":", "_Colon_")
    ret = ret.replace("(", "_LeftRound_")
    ret = ret.replace(")", "_RightRound_")
    ret = ret.replace("+", "_Plus_")
    ret = ret.replace("-", "_Minus_")
    return ret


def create_Keyword_class(kotlin_folder_name:str) -> str:
    ret = "package com.volupism." + kotlin_folder_name + "\n"
    ret += "\n"
    ret += "import com.volupism.s2_0.S2" + "\n"
    ret += "\n"
    ret += "/**" + "\n"
    ret += " * Created by Creator.py" + "\n"
    ret += " */" + "\n"
    ret += "class Keyword {" + "\n"
    keyword_list = list()
    add_item(keyword_list, "other_keyword.u")
    add_item(keyword_list, "base_model_keyword.u")
    ret += "\n"
    ret += "var keyword_s: S2<String>" + "\n"
    ret += "\n"
    ret += "var keyword_set: HashSet<String> = HashSet()" + "\n"
    ret += "\n"
    # constructor
    ret += "constructor() {" + "\n"
    ret += "// Created by Key_creator.py"
    ret += "\n" + "var keyword_list = listOf("
    sort_by_length(keyword_list)
    for keyword in keyword_list:
        ret += "\n" + "Key." + get_name(keyword) + ","
    ret += ")" + "\n"
    ret += "this.keyword_s = S2(keyword_list)" + "\n"
    for keyword in keyword_list:
        ret += "\n" + "keyword_set.add(Key." + get_name(keyword) + ")"
    ret += "\n" + "}"
    # end of class
    ret += "\n" + "}"
    # Write class
    filePath = "../src/main/kotlin/com/volupism/" + kotlin_folder_name + "/Keyword.kt"
    with open(filePath, "w", encoding="utf-8") as file:
        file.write(ret)
    print("File written : " + filePath)
    return ret


def create_Base_model_token_class(kotlin_folder_name :str) -> str:
    ret = "package com.volupism." + kotlin_folder_name + "\n"
    ret += "\n"
    ret += "/**" + "\n"
    ret += " * Created by Creator.py" + "\n"
    ret += " */" + "\n"
    ret += "class Base_model_token {" + "\n"
    keyword_list = list()
    add_item(keyword_list, "base_model_keyword.u")
    sort_by_unicode(keyword_list)
    ret += "\n"
    ret += "companion object {"
    for keyword in keyword_list:
        ret += "\n" + "@JvmStatic"
        ret += "\n" + "val " + get_name(keyword) + " = Token(Key." + get_name(keyword) + ")"
    ret += "}"
    # end of class
    ret += "\n" + "}"
    # Write class
    filePath = "../src/main/kotlin/com/volupism/" + kotlin_folder_name + "/Base_model_token.kt"
    with open(filePath, "w", encoding="utf-8") as file:
        file.write(ret)
    print("File written : " + filePath)
    return ret


def sort_by_length(keyword_list: list[str]):
    def comparator(a: str, b: str) -> int:
        if len(a) < len(b):
            return 1
        elif len(a) == len(b):
            return 0
        else:
            return -1

    keyword_list.sort(key=cmp_to_key(comparator))


def sort_by_unicode(item_list: list[str]):
    def comparator(a: str, b: str) -> int:
        if a < b:
            return -1
        elif a == b:
            return 0
        else:
            return 1

    item_list.sort(key=cmp_to_key(comparator))


kotlin_folder_name = "dutemo_data_4"
create_Key_class(kotlin_folder_name)
create_Keyword_class(kotlin_folder_name)
create_Base_model_token_class(kotlin_folder_name)
