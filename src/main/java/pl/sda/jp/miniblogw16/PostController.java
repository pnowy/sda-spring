package pl.sda.jp.miniblogw16;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.sda.jp.miniblogw16.post.PostDto;

@Controller
public class PostController {

    @GetMapping("/post/{postId}")
    public String showSinglePost(@PathVariable String postId, Model model) {

        PostDto postDto = new PostDto(Long.valueOf(postId), "Jakiś post");
        model.addAttribute("post", postDto);

        return "post/singlePost";
    }
}
