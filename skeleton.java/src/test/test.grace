fun main () : nothing
    var x : int;
    var y : int;
    fun p1 (b : char; ref c : int) : int;

    fun p1 (c : char; ref c : int) : int
    {
        return 3;
    }
    
{
    p1(x, y);
}
