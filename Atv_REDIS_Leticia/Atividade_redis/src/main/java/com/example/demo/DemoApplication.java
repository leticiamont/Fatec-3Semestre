    package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import redis.clients.jedis.Jedis;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Jedis jedis = new Jedis("redis://default:FE7AsmKnSqFUUIpoTwYCc1yreyVphvty@redis-16688.c308.sa-east-1-1.ec2.cloud.redislabs.com:16688");

        jedis.hset("tarefa", "1", "Ir para academia");
        jedis.hset("tarefa", "2", "Tomar café da manhã");
        jedis.hset("tarefa", "3", "Fazer almoço");


        System.out.println("Tarefas adicionadas.");

        System.out.println("\nLista de Tarefas:");
        jedis.hgetAll("tarefa").forEach((id, value) -> System.out.println(id + ": " + value));

        jedis.hset("tarefas_concluidas", "1", jedis.hget("tarefa", "1"));
        jedis.hdel("tarefa", "1");
        System.out.println("\nTarefa 1 concluída.");

        jedis.hdel("tarefa", "2");
        System.out.println("\nTarefa 2 removida.");

        jedis.close();
    }
}