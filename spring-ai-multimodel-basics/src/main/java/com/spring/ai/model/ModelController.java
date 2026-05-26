package com.spring.ai.model;

import org.springframework.ai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.ai.audio.transcription.AudioTranscriptionResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/model")
public class ModelController {
	@Autowired
	private ChatModel chatModel;

	@Autowired
	private OpenAiImageModel openAiImageModel;
	
	@Autowired
	private OpenAiAudioTranscriptionModel openAiAudioTranscriptionModel;
	
	@Autowired
	private OpenAiAudioSpeechModel openAiAudioSpeechModel;

	@GetMapping("/chat/{message}")
	public String chat(@PathVariable("message") String message) {
		return ChatClient.create(chatModel).prompt().user(message).call().content();
	}

	@GetMapping("/image/{prompt}")
	public String generateImage(@PathVariable("prompt") String prompt) {

		ImageResponse response = openAiImageModel.call(
				new ImagePrompt(prompt, OpenAiImageOptions.builder().model("gpt-image-1").width(1024).height(1024).quality("high").build()));

		return response.getResult().getOutput().getUrl();
	}

	@GetMapping("/image-to-text")
	public String imageToText() {

		return ChatClient
				.create(chatModel).prompt().user(user -> user.text("Explain what you see in this image")
						.media(MimeTypeUtils.IMAGE_JPEG, new FileSystemResource("src/main/resources/pexels-pixabay-270404.jpg")))
				.call().content();
	}
	
	/**
	 * 
	 * @return
	 */
	@GetMapping("/audio-to-text")
	public String audioToText() {
		 AudioTranscriptionPrompt prompt =
		            new AudioTranscriptionPrompt(
		                    new FileSystemResource(
		                            "src/main/resources/harvard.wav"));

		    AudioTranscriptionResponse response =
		            openAiAudioTranscriptionModel.call(prompt);

		    return response.getResult().getOutput();
	}
	
	/**
	 * 
	 * @param text
	 * @return
	 */
	@GetMapping("/text-to-audio/{text}")
	public ResponseEntity<Resource> textToAudio(
	        @PathVariable("text") String text) {
	    byte[] audioBytes = openAiAudioSpeechModel.call(text);

	    ByteArrayResource resource =
	            new ByteArrayResource(audioBytes);

	    return ResponseEntity.ok()
	            .header(HttpHeaders.CONTENT_DISPOSITION,
	                    "attachment; filename=speech.mp3")
	            .contentType(MediaType.APPLICATION_OCTET_STREAM)
	            .body(resource);
	}
	
	
	
}
