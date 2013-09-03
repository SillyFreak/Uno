/**
 * DefaultCardPainter.java
 * 
 * Created on 03.09.2013
 */

package net.slightlymagic.uno.gui;


import static java.lang.Math.*;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;

import net.slightlymagic.uno.UnoCard;


/**
 * <p>
 * {@code DefaultCardPainter}
 * </p>
 * 
 * @version V0.0 03.09.2013
 * @author SillyFreak
 */
public class DefaultCardPainter implements CardPainter {
    private static final Shape Card, Internal, Reverse, Skip;
    
    static {
        Card = new RoundRectangle2D.Double(-80, -120, 160, 240, 20, 20);
        
        {
            Path2D p = new Path2D.Double();
            p.moveTo(-60, -70);
            p.lineTo(-60, 10);
            p.curveTo(-60, 40, -30, 70, 0, 70);
            p.lineTo(60, 70);
            p.lineTo(60, -10);
            p.curveTo(60, -40, 30, -70, 0, -70);
            p.closePath();
            Internal = p;
        }
        {
            Path2D p = new Path2D.Double();
            p.moveTo(-10, -20);
            p.curveTo(-20, -20, -38, -10, -40, 0);
            p.curveTo(-50, -20, -25, -40, -10, -40);
            p.lineTo(20, -40);
            p.lineTo(20, -55);
            p.lineTo(45, -30);
            p.lineTo(20, -5);
            p.lineTo(20, -20);
            p.closePath();
            Reverse = p;
        }
        {
            Area a = new Area();
            a.add(new Area(new Ellipse2D.Double(-50, -50, 100, 100)));
            a.subtract(new Area(new Ellipse2D.Double(-35, -35, 70, 70)));
            a.add(new Area(new Rectangle(-8, -40, 16, 80)));
            a.transform(AffineTransform.getRotateInstance(PI / 4));
            Skip = a;
        }
    }
    
    @Override
    public void paintCard(Graphics2D g2d, UnoCard card, boolean back) {
        Color color;
        if(back) color = Color.GRAY;
        else switch(card.getColor()) {
            case UnoCard.BLACK:
                color = Color.BLACK;
            break;
            case UnoCard.BLUE:
                color = Color.BLUE;
            break;
            case UnoCard.GREEN:
                color = Color.GREEN;
            break;
            case UnoCard.RED:
                color = Color.RED;
            break;
            case UnoCard.YELLOW:
                color = Color.YELLOW;
            break;
            default:
                throw new AssertionError();
        }
        
        g2d.setColor(color);
        g2d.fill(Card);
        
        g2d.setStroke(new BasicStroke(5f));
        g2d.setColor(Color.BLACK);
        g2d.draw(Card);
        
        g2d.setColor(Color.WHITE);
        g2d.fill(Internal);
        
        if(!back) switch(card.getKind()) {
            case UnoCard.NUMBER: {
                String str = "" + card.getNumber();
                {
                    g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 82f));
                    int w = g2d.getFontMetrics().stringWidth(str);
                    int h = g2d.getFontMetrics().getAscent();
                    
                    g2d.setColor(Color.BLACK);
                    g2d.drawString(str, -w / 2f + 2, h / 3f + 2);
                }
                {
                    g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 80f));
                    int w = g2d.getFontMetrics().stringWidth(str);
                    int h = g2d.getFontMetrics().getAscent();
                    
                    g2d.setColor(color);
                    g2d.drawString(str, -w / 2f, h / 3f);
                }
                break;
            }
            case UnoCard.DRAW_TWO: {
                String str = "+2";
                {
                    g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 82f));
                    int w = g2d.getFontMetrics().stringWidth(str);
                    int h = g2d.getFontMetrics().getAscent();
                    
                    g2d.setColor(Color.BLACK);
                    g2d.drawString(str, -w / 2f + 2, h / 3f + 2);
                }
                {
                    g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 80f));
                    int w = g2d.getFontMetrics().stringWidth(str);
                    int h = g2d.getFontMetrics().getAscent();
                    
                    g2d.setColor(color);
                    g2d.drawString(str, -w / 2f, h / 3f);
                }
                break;
            }
            case UnoCard.REVERSE: {
                g2d.setColor(Color.BLACK);
                
                g2d.translate(2, 2);
                g2d.fill(Reverse);
                g2d.translate(-2, -2);
                g2d.rotate(PI);
                g2d.translate(-2, -2);
                g2d.fill(Reverse);
                g2d.translate(2, 2);
                
                g2d.setColor(color);
                
                g2d.fill(Reverse);
                g2d.rotate(PI);
                g2d.fill(Reverse);
                
                break;
            }
            case UnoCard.SKIP: {
                g2d.setColor(Color.BLACK);
                
                g2d.translate(2, 2);
                g2d.fill(Skip);
                g2d.translate(-2, -2);
                
                g2d.setColor(color);
                
                g2d.fill(Skip);
                
                break;
            }
            case UnoCard.WILD: {
                break;
            }
            case UnoCard.DRAW_FOUR: {
                String str = "+4";
                {
                    g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 80f));
                    int w = g2d.getFontMetrics().stringWidth(str);
                    int h = g2d.getFontMetrics().getAscent();
                    
                    g2d.setColor(color);
                    g2d.drawString(str, -w / 2f, h / 3f);
                }
                break;
            }
            default:
                throw new AssertionError();
        }
    }
}
