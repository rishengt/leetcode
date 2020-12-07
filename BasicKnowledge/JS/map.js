let map = new Map();
map.set("kkk",3);
map.set("", {phone: 1234, address: "1234"});
for(let [key,value] of map){
    console.log(key,value);
}
