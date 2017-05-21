fun main () : nothing

	fun p2 (ref h :int[1]): int;

	fun p3 (c :int) : char;


    fun p2 (ref h : int[1]) : int
    {
        h <- p3();
         
    }

{}
