function sum() {
    return Array.prototype.reduce.call(arguments, function(a, b) {
        return a + b;
    }, 0);
}
