# SpringMVC注解

1. @Controller:在当前类使用，相当于 implements Controller。

2. @RequestMapping(""):浏览器访问的路径，可以加在类上或者方法上。

3. @PathVariable:放在方法行参上，用于RestFul风格。

   ```java
   @GetMapping("Param/p1/{a}/{b}")
   public String p1(@PathVariable int a, @PathVariable int b, Model model) {
       int result = a + b;
       model.addAttribute("msg","结果是："+result);
       return "hello";
   }
   ```

4. @GetMapping():get方法。

5. @PostMapping():post方法。

6. @DeleteMapping():delete方法。

7. @PutMapping():put方法。

8. @RequestParam("xx")：放在行参旁边，并且可以定义该行参在前端中传值的参数名。

9. @ResponseBody:用在方法上，使该方法不经过视图解析器，return是什么就是什么。

7. @RestController:用在类上，类里面的所有方法都不经过视图解析器。