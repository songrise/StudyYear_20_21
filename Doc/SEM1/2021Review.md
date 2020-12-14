# 2021 review
Lec01: Slides 30—32
Lec02: All except slide 11
Lec03: All except slides 14 and 15
Lec04: All except slides 29 through 38
Lec05: All included
Lec06: All included
Lec07: All included
Lec08: All included
Lec09: All except slides 34 through 37
Lec10: All included


lec2
input 
side-effect  
numeric type conversion  

lec3  
- Assignment For reference variables, the address is copied
- Comparison (==) For reference variables, the addresses are compared
- Local variables or formal parameters, unlike fields, cannot be hidden   
- Object fields are always set to 0’s by default (方法中的变量不是)
- One constructor may call another to partially initialize an object *The call has to be the first statement*
- initialize order; 1.initializer 2. constructor
- Methods of the same name can be declared in the same class, as long as they have different signatures
- 27 Parameter names and the return type, however, arenot
part of the signature!!! 所以只有返回类型不同不行, 参数顺序不同可以
- 30 There is exactly one copy of static fields, no matter how many objects there are created(0,1,N)
- No "this" in a static method
- initialization of static fields: p 33.

lec 4:
- 访问权限 p.9
- 14 final protects only the variable itself, If the variable is a reference, final does not protect the
object that reference points to
- 19 The default class loader searches for classes using a list of paths defined in “classpath”
- 23 Importing all classes from a package does not import the
classes from its subpackages

lec 05.
- 10 String are immutable

lec 06 
- A class C can only inherit from those classes that are visible to C
- A member M of a superclass is inherited to a subclass means the member can be accessed in the subclass using its name
- What Are/Are Not Inherited? (1) p.8
- 12 Super-Class’s Constructor Is Always Invoked 
- 13 构造器顺序
- 22 The type of an object is decided by the class whose constructor was called when creating the object.
- 25 At runtime, the actual method executed by a method call depends on the dynamic type of the receiver of that call
- 27 name binding.
- Accesses to instance variables, static members (both fields and methods) are always statically bound
  
lec 7 
- A class with at least one abstract method must be declared
abstract
- 12 Abstract methods are meant to be overridden (cannot overiden then should not be abstract)
- 15 The super-class of an abstract class may be concrete
- Although cannot be instantiated, abstract classes need to have the right constructors as required by constructor chaining\
- 18 interface  

lec 9 
- 7 Which Catch Clause Should Be Used?
- 30 wildcards
- 33 Wildcards and Subtyping